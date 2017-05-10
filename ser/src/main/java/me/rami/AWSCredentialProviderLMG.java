package me.rami;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;



public class AWSCredentialProviderLMG implements AWSCredentialsProvider {

	
    private static final Logger LOGGER = LoggerFactory.getLogger(AWSCredentialProviderLMG.class);
    
	// Credentials long terme fourni par les admins 
    AWSCredentials Lcredentials = null;
	// Credential temporaire
    AWSCredentials Tcredentials = null;
	public AWSCredentialProviderLMG()
	{
		
	}


	public AWSCredentialProviderLMG(AWSCredentials awsCre, corePropertiesConfiguration propertie)
	{
		Lcredentials = awsCre;
        AWSSecurityTokenServiceClient stsClient = new  AWSSecurityTokenServiceClient(Lcredentials);
        LOGGER.info("Creation du client STS ..............");
        propertie.affiche();
        //
        AssumeRoleRequest assumeRequest = new AssumeRoleRequest()
                .withRoleArn("arn:aws:iam::347970623729:role/dae_from_support")
                .withDurationSeconds(3600)
                .withRoleSessionName("blabla");
        LOGGER.info("Creation de la requête pour le role ..............");

        //
        AssumeRoleResult assumeResult = stsClient.assumeRole(assumeRequest);
        Tcredentials =
        new BasicSessionCredentials(
                    assumeResult.getCredentials().getAccessKeyId(),
                    assumeResult.getCredentials().getSecretAccessKey(),
                    assumeResult.getCredentials().getSessionToken());
	}

	@Override
	public AWSCredentials getCredentials() {
		// TODO Auto-generated method stub
		return Tcredentials;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	

}
