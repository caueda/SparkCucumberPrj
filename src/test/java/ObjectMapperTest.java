import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.API;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectMapperTest {
    @Test
    public void testMapToJson() throws JsonProcessingException {
        Map<String, List<String>> map = Map.of("2022-10-21", List.of("1", "2"));
        ObjectMapper mapper = new ObjectMapper();
        var json = API.unchecked(() -> {
            return mapper.writeValueAsString(map);
        }).get();
        System.out.println(json);

        Map<String, List<String>> mapFromJson = mapper.readValue(json, HashMap.class);
        System.out.println(mapFromJson);
    }
}
