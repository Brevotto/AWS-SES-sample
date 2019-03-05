package com.amazonaws.samples;
/*
 * Copyright 2014-2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

import java.io.IOException;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

public class AmazonSESSample {

	  // Replace sender@example.com with your "From" address.
	  // This address must be verified with Amazon SES.
	  static final String FROM = "sbudidharma@healthforcepartners.com";

	  // Replace recipient@example.com with a "To" address. If your account
	  // is still in the sandbox, this address must be verified.
	  static final String TO = "sbudidharma@healthforcepartners.com";

	  // The configuration set to use for this email. If you do not want to use a
	  // configuration set, comment the following variable and the 
	  // .withConfigurationSetName(CONFIGSET); argument below.
	  //static final String CONFIGSET = "ConfigSet";

	  // The subject line for the email.
	  static final String SUBJECT = "Amazon SES test (AWS SDK for Java)";
	  
	  // The HTML body for the email.
	  static final String HTMLBODY = "<h1>Amazon SES test (AWS SDK for Java)</h1>"
	      + "<p>This email was sent with <a href='https://aws.amazon.com/ses/'>"
	      + "Amazon SES</a> using the <a href='https://aws.amazon.com/sdk-for-java/'>" 
	      + "AWS SDK for Java</a>";

	  // The email body for recipients with non-HTML email clients.
	  static final String TEXTBODY = "This email was sent through Amazon SES "
	      + "using the AWS SDK for Java.";

	  public static void main(String[] args) throws IOException {

	    try {
	    	
	    	AWSCredentials awsCredentials = new BasicAWSCredentials("xxxxxx", "xxxxxxx");  //accessKey and secretKey
			
			//AmazonSimpleEmailService client = new AmazonSimpleEmailServiceClient(awsCredentials);
	      AmazonSimpleEmailService client = 
	          AmazonSimpleEmailServiceClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider (awsCredentials))
	          // Replace US_WEST_2 with the AWS Region you're using for
	          // Amazon SES.
	            .withRegion(Regions.US_WEST_2).build();
	      SendEmailRequest request = new SendEmailRequest()
	          .withDestination(
	              new Destination().withToAddresses(TO))
	          .withMessage(new Message()
	              .withBody(new Body()
	                  .withHtml(new Content()
	                      .withCharset("UTF-8").withData(HTMLBODY))
	                  .withText(new Content()
	                      .withCharset("UTF-8").withData(TEXTBODY)))
	              .withSubject(new Content()
	                  .withCharset("UTF-8").withData(SUBJECT)))
	          .withSource(FROM);
	          // Comment or remove the next line if you are not using a
	          // configuration set
	          //.withConfigurationSetName(CONFIGSET);
	      client.sendEmail(request);
	      System.out.println("Email sent!");
	    } catch (Exception ex) {
	      System.out.println("The email was not sent. Error message: " 
	          + ex.getMessage());
	    }
	  }
}
