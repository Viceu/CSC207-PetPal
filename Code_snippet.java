package api;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
public class Code_snippet {
    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    MediaType mediaType = MediaType.parse("text/plain");
    RequestBody body = RequestBody.create(mediaType, "");
    Request request = new Request.Builder()
            .url("")
            .method("GET", body)
            .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJyZFJwbExMcFV2SlYwb1piMUsxNklUSWMyejFNaTJZbDV3cnZnbkRSaThkVHJ0NEdibSIsImp0aSI6IjZmZjhkZTA1ODMyZjFhM2I2Zjg3YjYyOTUyMDYxNGY4ODM0NTllZjc0MzUwNjIxMzcwNTZiZmNiYzEzMmMzZjYwMTc1MGE1ZjlkZGU5ODQ2IiwiaWF0IjoxNjk2MDIzMTg4LCJuYmYiOjE2OTYwMjMxODgsImV4cCI6MTY5NjAyNjc4OCwic3ViIjoiIiwic2NvcGVzIjpbXX0.Oq9Zlvji4JoEbQCwxK1hxGjPVZH3sckDAega4r_RYQv5S-KSyk6sXEFWAxdlxg8oXnhBGIJIz9nNJ9yYQcYB7CJGosAhz1_Y9NA1XBpGd3R1DHgISC7TvRhtNbiZhqVjyEevmZozlw5zIw9B1QHdHCgCOfB5CdNRg0yElO8GozCY64E_bige_9WlxUHVwOhR62Feiaetc3hEJ187bkqmSbq9QDlpXGHBlQHTjF0eKilkV8EX84FK1FApgZT3UAt8ov83GqbV_uB4Ql23h2kT6yqqWhonn1dYV9fmH_lVtRv14L3pT0QpBRP_PeCFD7FUAIIz6BArYVKq2nMPQInigQ")
            .build();
    Response response = client.newCall(request).execute();
}
