package br.com.gsw.slack.sonar.notifier.sonar.web.model;

public class ResourceResponseFixture {
    public static ResourceResponse newRatings() {
        final ResourceResponse resource = new ResourceResponse();
        resourcesDefault(resource);
        resource.setMsr(MsrResponseFixture.newRatings());
        return resource;
    }

    public static ResourceResponse newDuplications() {
        final ResourceResponse resource = new ResourceResponse();
        resourcesDefault(resource);
        resource.setMsr(MsrResponseFixture.newDuplications());
        return resource;
    }

    public static ResourceResponse newTests() {
        final ResourceResponse resource = new ResourceResponse();
        resourcesDefault(resource);
        resource.setMsr(MsrResponseFixture.newTests());
        return resource;
    }

    private static void resourcesDefault(final ResourceResponse resource) {
        resource.setId("1");
        resource.setKey("br.com.gsw:slack-pusher");
        resource.setName("Slack Pusher");
        resource.setVersion("1.0-RELEASE");
        resource.setDescription("Slack Pusher using Sonarqube stats");
    }
}