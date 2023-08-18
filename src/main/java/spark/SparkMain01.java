package spark;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.*;

import java.util.ArrayList;
import java.util.List;

public class SparkMain01 {
    public static void main(String[] args) {
        List<Double> inputData = new ArrayList<>();
        inputData.add(35.5);
        inputData.add(12.49943);
        inputData.add(90.32);
        inputData.add(20.32);

        SparkSession spark = SparkSession.builder()
                .appName("SparkMain02")
                .master("local[*]")
                .getOrCreate();

        JavaRDD<Double> rdd;
        try (JavaSparkContext sc = JavaSparkContext.fromSparkContext(spark.sparkContext())) {
            rdd = sc.parallelize(inputData);
            var sum = rdd.reduce(Double::sum);
            System.out.println("Result: " + sum);
        }
    }
}
