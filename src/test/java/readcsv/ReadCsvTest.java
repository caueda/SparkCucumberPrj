package readcsv;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@ExtendWith(SpringExtension.class)
@Slf4j
public class ReadCsvTest {
    @Test
    public void testReadCsv() throws Exception {
        Resource resource = new ClassPathResource("classpath:rowproducer/rowProducer.csv");
        InputStream inputStream = resource.getInputStream();
        try {
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            String data = new String(bdata, StandardCharsets.UTF_8);
            log.info(data);
        } catch (IOException e) {
            log.error("IOException", e);
        }
    }
}
