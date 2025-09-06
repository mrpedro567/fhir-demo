import express from 'express'
import {v4} from 'uuid';
import { Kafka, logLevel } from 'kafkajs';

const app = express();
const port = 9099;
const topic = "fhir";

app.use(express.json());

const kafka = new Kafka({
  brokers: ['kafka:9092', 'kafka:29092'],
  logLevel: logLevel.WARN,
});

const producer = kafka.producer();

try{
  await producer.connect();
  console.log("Connected to Kafka");
}
catch(e){
  console.error("Error connecting to Kafka", e);
  process.exit(1);
}

app.post('/', async (req, res) => {
  try{ 
    const key = v4();
    await producer.send({
      topic,
      messages: [{
        key,
        value: JSON.stringify(req.body)
      }],
      acks: 1
    });
    res.status(200).send("Message sent to Kafka");
  }
  catch(e){
    console.error("Error sending message to Kafka", e);
    res.status(500).send("Error sending message to Kafka");
  }
});

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`);
});