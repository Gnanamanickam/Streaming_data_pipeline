package Utils

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider

import java.io.IOException
import com.amazonaws.regions.Regions
import com.amazonaws.services.simpleemail.{AmazonSimpleEmailService, AmazonSimpleEmailServiceClient, AmazonSimpleEmailServiceClientBuilder}
import com.amazonaws.services.simpleemail.model.Body
import com.amazonaws.services.simpleemail.model.Content
import com.amazonaws.services.simpleemail.model.Destination
import com.amazonaws.services.simpleemail.model.Message
import com.amazonaws.services.simpleemail.model.SendEmailRequest
import com.typesafe.config.ConfigFactory

import scala.collection.JavaConverters._


object AwsEmailService {

  // Get the config values from application.conf in resources
  val config = ConfigFactory.load("Application.conf").getConfig("sparkStreaming")

  // The email address verified in AWS account
  val DefaultSourceEmailAddress:String = config.getString("sourceAddress")
  // To email address
  val targetAddressList: List[String] = List(config.getString("targetAddressList"))
  // The subject line for the email.
  val subject = config.getString("subject")
  // The body for the email.
  val messageBody: Body = new Body(new Content(config.getString("subjectBody")))
  // destination email address
  val destination:Destination = new Destination(targetAddressList.asJava)

  val message:Message =
    new Message(new Content(subject), messageBody)

  /**
   * A handle on SES with credentials fetched from the environment variables
   *
   *     AWS_ACCESS_KEY_ID
   *     AWS_SECRET_KEY
   */
//  protected lazy val simpleEmailService:AmazonSimpleEmailServiceClient =
//    new AmazonSimpleEmailServiceClient(new EnvironmentVariableCredentialsProvider());



  @throws[IOException]
  def main(args: Array[String]): Unit = {
    try {
      val client = AmazonSimpleEmailServiceClientBuilder.standard.withRegion(Regions.US_EAST_1).build()
      val request = new SendEmailRequest(DefaultSourceEmailAddress, destination, message)
      client.sendEmail(request)
      System.out.println("Email sent")
    } catch {
      case ex: Exception =>
        System.out.println("The email was not sent. Error message: " + ex.getMessage)
    }
  }
}