package io.bookquest.usecase;

import io.bookquest.config.security.JwtComponent;
import io.bookquest.config.security.UserSecurity;
import io.bookquest.entrypoint.v1.dto.UserEntrypoint;
import io.bookquest.entrypoint.v1.integration.database.dto.CategoryRecord;
import io.bookquest.entrypoint.v1.integration.database.dto.UserDataTransfer;
import io.bookquest.entrypoint.v1.integration.mail.MailClient;
import io.bookquest.entrypoint.v1.integration.mail.dto.MailDto;
import io.bookquest.entrypoint.v1.integration.mail.dto.MessageMailDto;
import io.bookquest.entrypoint.v1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@Service
public class UserService {

    public static final Random RANDOM = new Random();
    @Autowired
    private DatabaseRepository databaseRepository;

    @Autowired
    private S3Client s3Client;

    @Autowired
    private JwtComponent jwtComponent;

    @Autowired
    private MailClient mailClient;

    @Value("${mail.courier.key}")
    private String courierKey;

    //pending bcrypt to save e compare
    public String login(String username, String password) {
        var user = databaseRepository.getUser(username);
        var authorize = user.getSenha()
                .equalsIgnoreCase(password);

        if (!authorize)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        return jwtComponent.generateToken(UserSecurity.toUserDetails(username, user.getSenha()));
    }

    public void createAccount(UserEntrypoint userEntrypoint) {
        UserDataTransfer user = UserMapper.toCreateUserDto(userEntrypoint);
        databaseRepository.saveCreate(userEntrypoint.username(), user);
    }

    public String uploadFile(MultipartFile file, String username) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = username + "_image";
        PutObjectRequest objectS3 = PutObjectRequest.builder()
                .bucket("bookquest-images")
                .key(fileName)
                .build();
        PutObjectResponse putObjectResponse = s3Client.putObject(objectS3, fileObj.toPath());

        return fileObj.delete() ? fileName : null;
    }

    public String getAvatarPicture(String fileName) {
        var s3Object = s3Client.getObjectAsBytes(GetObjectRequest.builder().bucket("bookquest-images").key(fileName.concat("_image")).build());
        return Base64.getEncoder().encodeToString(s3Object.asByteArray());
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        if (Objects.isNull(file.getOriginalFilename()))
            throw new RuntimeException("Arquivo sem nome");

        String fileName = file.getOriginalFilename();

        File convertedFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return convertedFile;
    }

    public UserEntrypoint getUser(String username) {
        var user = databaseRepository.getUser(username);
        CategoryRecord userClass = databaseRepository.getClass(user.getClasseOutput());
        return UserMapper.toEntrypoint(user, userClass.name());
    }

    public String getUserByEmail(String email) {
        return databaseRepository.getUserByEmail(email).getName();
    }

    public void updateUser(String idUser, UserEntrypoint user) {
        if (user.senha() != null) {
            // verify if user has otp available e validate
        }

        databaseRepository.saveCreate(idUser, UserMapper.updateInfo(user));
    }

    public void sendOtp(String email) {
        var user = getUserByEmail(email);
        var otpNumber = RANDOM.nextInt(0, 999999);

        var toMail = Map.of("email", email);
        var dataVariable = Map.of("recipientName", user, "otpCode", otpNumber);
        var messageMailDto = new MessageMailDto(toMail, dataVariable);
        var mailDto = new MailDto(messageMailDto);
        mailClient.sendMail(mailDto, "Bearer ".concat(courierKey));
    }

    public void validateOtp(String otpNumber) {


    }
}
