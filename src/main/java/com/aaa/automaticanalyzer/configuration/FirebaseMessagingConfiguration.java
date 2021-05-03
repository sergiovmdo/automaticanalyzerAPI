package com.aaa.automaticanalyzer.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;

@Component
@Slf4j
public class FirebaseMessagingConfiguration implements ApplicationRunner {
    private String firebaseProject = "automatic-analyzer";

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        log.debug("Initializing project {} from firebase", firebaseProject);

        String s = "{\n" +
                "  \"type\": \"service_account\",\n" +
                "  \"project_id\": \"automatic-analyzer\",\n" +
                "  \"private_key_id\": \"7a9eda65b5dffb93b070952bf7ae02475acef491\",\n" +
                "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDXQfRxXRNK0cZd\\nkzf7ODIey9M9frcEOukZKZ0dNmYtdPBlsiJQsnj4v7Q7S7jedY8XQ8pG3FhJeza6\\nHNds65qxtXvaHwWOKr/L2+22db6rd7VsifEKdULVLf7Yj/h3pxk8XEF0san9RMUG\\nmY7kBC1YPmFd0Yd1P90WbpujBp8vpXRt7cEGfhQe1XUgTn7J0PhNHVuu3dFTCZno\\nSA1OAGdatektEygIy558OXx/Z/hfs6RB/AQrubphYIBRRGh4wQ4T7TJPBVXfMNkR\\ncd+FA5RNrfz2DltHPJ3Ff+m4p4BEOlzU/dGDSMKmWh9gPKp2SMCjThOXUR8Jk5qb\\nSwTZD58zAgMBAAECggEAMAZNgJtI6hjqPkdRppFPkVJdMyuZrm5sQgpoPLO90Sd1\\nWOMIx+NBnRG8JryKpssonQKpVuzjH6XHPHeh0nGwJjS8yJnCNgkwmE1deCdF3mCL\\nUAdoiTJHMmOcJ3nbb+qOdUqJ6fyXVeOpZ4ML/7A046qrM3DDXXPdohsUSyd3jXvd\\n/RWmybmOvt8Mu3XVpO7GONPcIvb4WRLwvrV+ByErZHCfdqZx2b7xTFR0phcArXEG\\n3Kd2EwkupcnEbRG2weVgnWvkupNPxNnmfxur1Im9AFewpKvmhNA+0jo1PmCEOG2C\\nU/PEwwIYWL/DLu301JEQCQViktaYN2bgrfmk88VlWQKBgQD8O1XpGRdgR1hYksKA\\nhGOKHMKSPrEgGzb/v/poM7RoIXCoIMXqGI4OhWnXVk7q1jTwIW6Vwqt6xV9xXlj7\\nBBZrHiLLTZuyc+4K4RAMJba+jVInC1O/VMtchdpx1HcLkxF6XPiOgo9B6xsBN6Hw\\nAOA/xCH8MqUd8SZIevPnWFSW+wKBgQDaeTYI39oyljZFPg6oNmFfhhVZU2gDo2y0\\nwkk8xsKx+XXt54JhT5qcPdRqpX4rF6R/w9JtmuOV/Of9xGfsrdaML2SxbQBfFuA+\\ng685EQcAdAA/xEnQ+fdnAY+TEl8+2IGGiFY1V2Telpl0M1onEC9WJBHGRcrzFHft\\n9EiILvODKQKBgQDkW7wOC990FWPQ3R4nSLTt59fom8W1Ma57oY3OJXGiDRR3wyQs\\nKwsB7pvCj6tnRYQsG7iI8fRadLNSan7Y0/yQAnqx4ZOi3v6W7uieds6aekv0eXba\\n9KQhIjaBdEBLRF1AkUefjmiw33BVi86bAdAFK0cxk5CWKSvl1xtXSktbXQKBgQDJ\\nc8F2l2us/8PVP27mfxZ0rb/b2UPtLvGbEBfWOfTnhX7Pu40GzA9mCMFpAWcAt1FR\\nGrFbrdozwLvuzP+DR5yxMTZpQHh6Ni7aljSpq2DIcy6Tnhzu3L/btJ2+YM+AtMVi\\n1y6fCxIRCc9ATeXQZWUwyhu1tz93u6UGvx1urdSPWQKBgQDUOHw0jBqPTUDJ7R8D\\noXhfTXBEE6UNceIMvZ2CoUMb4Us+Ri8OwtG6WvJp/f1rGbq18uPpS/iANcNw82aN\\nM+ZIJi5G9DMv2TJiTOY7ij4PubHkgD5to1tAarvTtqNAqF9rYaxC27eRCT8M/ltu\\nR72TMaA/ObBdxYo6cQ4Hv+7Q5w==\\n-----END PRIVATE KEY-----\\n\",\n" +
                "  \"client_email\": \"firebase-adminsdk-sxh8o@automatic-analyzer.iam.gserviceaccount.com\",\n" +
                "  \"client_id\": \"101606959964289401531\",\n" +
                "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-sxh8o%40automatic-analyzer.iam.gserviceaccount.com\"\n" +
                "}";

        InputStream targetStream = new ByteArrayInputStream(s.getBytes());

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(new DataInputStream(targetStream)))
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
            log.debug("Project {} initialized", firebaseProject);
        } else {
            log.debug("Project {} was already initialized, skipping", firebaseProject);
        }
    }
}
