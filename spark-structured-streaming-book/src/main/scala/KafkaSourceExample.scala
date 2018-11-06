/**
  ./bin/kafka-console-producer.sh \
    --topic topic1 \
    --broker-list localhost:9092 \
    --property parse.key=true \
    --property key.separator=,
*/
import org.apache.spark.sql.streaming.{OutputMode, Trigger}
import scala.concurrent.duration._
val fromKafkaTopic1ToConsole = spark.
  readStream.
  format("kafka").
  option("subscribe", "topic1").
  option("kafka.bootstrap.servers", "localhost:9092").
  option("startingoffsets", "earliest").  // latest, earliest or JSON with {"topicA":{"part":offset,"p1":-1},"topicB":{"0":-2}}
  load.
  select($"key" cast "string", $"value" cast "string"). // deserialize records
  as[(String, String)].
  writeStream.
  trigger(Trigger.ProcessingTime(10.seconds)).
  queryName("from-kafka-to-console").
  outputMode(OutputMode.Append).
  format("console").
  start

// ...after some time
fromKafkaTopic1ToConsole.stop
