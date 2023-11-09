package project.AbstractComponents;

import java.io.IOException;
import java.util.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
public class AzureDevOpsIntegration {

    public void updateTestCaseStatus(String testCaseId, String status,String errorMessage) throws IOException {
        String personalAccessToken = "n6lglntm5ebwf7ra3lzb6rcacmpxburtdobiemhsq7o64swc4kda";
        String organizationName = "ldd";
        String projectName = "Testing";
        String updateUrl = String.format("https://dev.azure.com/%s/%s/_apis/wit/workitems/%s?api-version=5.0",
                organizationName, projectName, testCaseId);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPatch httpPatch = new HttpPatch(updateUrl);
            httpPatch.setHeader(HttpHeaders.AUTHORIZATION,
                    "Basic " + Base64.getEncoder().encodeToString((":" + personalAccessToken).getBytes()));
            httpPatch.setHeader(HttpHeaders.CONTENT_TYPE, "application/json-patch+json; charset=utf-8");

            String requestBody = "[{ " +
                    "\"op\": \"add\", " +
                    "\"path\": \"/fields/Custom.7af23cf5-4928-40bb-9796-02e350f76840\", " +
                    "\"value\": \"" + status + "\" " +
                    "}, " +
                    "{ " +
                    "\"op\": \"add\", " +
                    "\"path\": \"/fields/Custom.ReasonforFail\", " +
                    "\"value\": \"" + errorMessage + "\" " +
                    "}]";
            httpPatch.setEntity(new StringEntity(requestBody));
            try (CloseableHttpResponse response = httpClient.execute(httpPatch)) {
                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    System.out.println("!!!!!Testcase id =" + testCaseId + " "
                            + "Automation status updated successfully in Azure DevOps.!!!!!");
                } else {
                    String responseContent = EntityUtils.toString(response.getEntity());
                    System.err.println("Failed to update work item. Response code: " + statusCode);
                    System.err.println("Response content: " + responseContent);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
