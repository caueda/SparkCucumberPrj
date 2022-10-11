package readcsv;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.FileCopyUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ExtendWith(SpringExtension.class)
@Slf4j
public class ReadCsvTest {
    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void testReadCsv() throws Exception {
        var resource = resourceLoader
                .getResource("classpath:rowproducer/rowProducer.csv");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()));
            String line;
            String header = null;
            List<String> sql = new ArrayList<>();
            while((line = reader.readLine()) != null) {
                String[] values = line.split(";");
                StringBuilder select = new StringBuilder();
                if(header == null) {
                    for(int i=0; i<values.length; i++) {
                        select.append("'%s' as " + values[i] + ((i<i-1) ? ", " : " "));
                    }
                    header = "select " + select.toString();
                } else {
                    sql.add(String.format(header, values));
                }
            }
            System.out.println(sql.stream().collect(Collectors.joining("\nunion all \n")));
        } catch (IOException e) {
            log.error("IOException", e);
        }
    }

    @Test
    public void saltTest() {
        var list = IntStream.range(0, 10).boxed()
                .map(n -> org.apache.spark.sql.functions.lit(n))
                .collect(Collectors.toList());
        System.out.println(list);
    }
}
