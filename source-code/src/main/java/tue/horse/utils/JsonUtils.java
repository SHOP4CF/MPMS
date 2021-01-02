package tue.horse.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.json.Json;

import java.io.IOException;
/**
 * Created by ktraganos on 4-4-2017.
 */
public final class JsonUtils {

    public static boolean isJSONValid(String jsonInString ) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonInString);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
