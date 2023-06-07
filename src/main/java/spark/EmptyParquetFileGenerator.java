package spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;

public class EmptyParquetFileGenerator {
    public static void main(String[] args) {
        // Initialize SparkSession
        SparkSession spark = SparkSession.builder()
                .appName("Empty Parquet File Generator")
                .master("local[*]")
                .config("spark.ui.enabled", "false")
                .getOrCreate();

        // Define the schema for the empty Parquet file
        StructType schema = new StructType()
                .add("id", "int")
                .add("name", "string")
                .add("age", "int");

        // Create an empty DataFrame with the defined schema
        Dataset<Row> emptyDataFrame = spark.createDataFrame(spark.sparkContext().emptyRDD(Encoders.bean(Row.class).clsTag()), schema);

        // Write the empty DataFrame to a Parquet file
        emptyDataFrame.write().parquet("/Users/carlosrobertoueda/parquet_files/empty_file2.parquet");

        // Stop the SparkSession
        spark.stop();
    }
}

