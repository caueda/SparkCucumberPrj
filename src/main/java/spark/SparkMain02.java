package spark;

import org.apache.spark.sql.SparkSession;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SparkMain02 {
    public static final int BATCHNAME = 0;
    public static final int SCENARIO = 1;
    public static final int PVTOSEN = 2;
    public static final int POSANDPE = 3;

    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("SparkMain02")
                .master("local[*]")
                .getOrCreate();

        var rowProducer = spark.read()
                .option("header", true)
                .option("inferSchema", true)
                .csv("src/main/resources/rowproducer/rowProducer.csv")
                .where("batchName='LONDON'")
                .select("batchName", "scenario", "pvToSen", "posAndPE")
                .collectAsList().stream()
                .flatMap(row -> Stream.of(
                        row.getString(BATCHNAME),
                        row.getString(SCENARIO),
                        row.getString(PVTOSEN),
                        row.getString(POSANDPE)
                ))
                .collect(Collectors.toList());

        System.out.println(rowProducer.get(BATCHNAME));
        System.out.println(rowProducer.get(POSANDPE));
    }
}
