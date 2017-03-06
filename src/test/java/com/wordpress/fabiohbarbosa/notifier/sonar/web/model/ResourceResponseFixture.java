package com.wordpress.fabiohbarbosa.notifier.sonar.web.model;

public class ResourceResponseFixture {
    public static ResourceResponse newRatings(final SqaleRating sqaleRating) {
        final ResourceResponse resource = new ResourceResponse();
        resourcesDefault(resource);
        resource.setMsr(MsrResponseFixture.newRatings(sqaleRating.name()));
        return resource;
    }

    public static ResourceResponse newRatings() {
        final ResourceResponse resource = new ResourceResponse();
        resourcesDefault(resource);
        resource.setMsr(MsrResponseFixture.newRatings("A"));
        return resource;
    }

    public static ResourceResponse newDuplications() {
        final ResourceResponse resource = new ResourceResponse();
        resourcesDefault(resource);
        resource.setMsr(MsrResponseFixture.newDuplications());
        return resource;
    }

    public static ResourceResponse newDuplications(final Integer totalFile) {
        final ResourceResponse resource = new ResourceResponse();
        resourcesDefault(resource);
        resource.setMsr(MsrResponseFixture.newDuplications(totalFile));
        return resource;
    }

    public static ResourceResponse newTests() {
        final ResourceResponse resource = new ResourceResponse();
        resourcesDefault(resource);
        resource.setMsr(MsrResponseFixture.newTests());
        return resource;
    }

    public static ResourceResponse newTests(final Integer total, final Integer errors, final Integer failures, final Integer skipped, final Double success, final Double coverage) {
        final ResourceResponse resource = new ResourceResponse();
        resourcesDefault(resource);
        resource.setMsr(MsrResponseFixture.newTests(total, errors, failures, skipped, success, coverage));
        return resource;
    }

    private static void resourcesDefault(final ResourceResponse resource) {
        resource.setId("1");
        resource.setKey("com.wordpress.fabiohbarbosa:slack-sonar-notifier");
        resource.setName("Slack Pusher");
        resource.setVersion("1.0-RELEASE");
        resource.setDescription("Slack Pusher using Sonarqube stats");
    }
}