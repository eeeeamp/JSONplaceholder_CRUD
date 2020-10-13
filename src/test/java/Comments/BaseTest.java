package Comments;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;

public class BaseTest {

    int randomPostId;
    int randomCommentId;
    String fakeName;
    String fakeEmail;
    String fakeBody;

    private static Random r;
    private static Faker f;
    static JSONObject jsonRequest;

    @BeforeAll
    public static void beforeAll() {

        r = new Random();
        f = new Faker();
        jsonRequest = new JSONObject();

    }

    @BeforeEach
    public void beforeEach() {

        randomPostId = r.nextInt(100) + 1;
        randomCommentId = r.nextInt(500) + 1;
        fakeName = f.funnyName().name();
        fakeEmail = f.internet().emailAddress();
        fakeBody = f.witcher().quote();


        jsonRequest.put("postId", randomPostId);
        jsonRequest.put("name", fakeName);
        jsonRequest.put("email", fakeEmail);
        jsonRequest.put("body", fakeBody);

    }
}
