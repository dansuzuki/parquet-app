package me.dan.parquet.app

import org.apache.avro.{ Schema, SchemaBuilder }
import org.apache.avro.generic.GenericData

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path

import org.apache.parquet.avro.AvroParquetWriter
import org.apache.parquet.hadoop.ParquetWriter
import org.apache.parquet.hadoop.metadata.CompressionCodecName

object Write extends App {
  println("This is the writer app")
  val fileToWrite = new Path("sample.parquet")
  val schema: Schema = SchemaBuilder.record("ARecord")
    .namespace("me.dan.schema.avro")
    .fields().requiredString("c1").requiredString("c2")
    .endRecord()

  val writer: ParquetWriter[GenericData.Record] = AvroParquetWriter
    .builder(fileToWrite)
    .withSchema(schema)
    .withConf(new Configuration())
    .withCompressionCodec(CompressionCodecName.SNAPPY)
    .build()

  (1 to 10).toList
    .map(n => {
      val record = new GenericData.Record(schema)
      record.put("c1", n.toString)
      record.put("c2", "%010d".format(n))
      record
    })
    .foreach(record => writer.write(record))

  writer.close()
}
