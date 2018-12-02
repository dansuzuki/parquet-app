package me.dan.parquet.app

import org.apache.avro.{ Schema, SchemaBuilder }
import org.apache.avro.generic.GenericData

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path

import org.apache.parquet.avro.AvroParquetReader
import org.apache.parquet.hadoop.ParquetReader
import org.apache.parquet.hadoop.metadata.CompressionCodecName

object Read extends App {
  val schema = Write.schema
  val fileToRead = new Path("sample.parquet")

  val reader = AvroParquetReader
    .builder(fileToRead)
    .withConf(new Configuration())
    .build()

  Iterator.continually(reader.read())
    .takeWhile(_ != null)
    .foreach(println)

  reader.close()
}
