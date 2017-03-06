package com.wordpress.fabiohbarbosa.notifier.slack.adapter;

import com.wordpress.fabiohbarbosa.notifier.PrepareFactoryTests;
import com.wordpress.fabiohbarbosa.notifier.scm.model.Scm;
import com.wordpress.fabiohbarbosa.notifier.scm.model.ScmFixture;
import com.wordpress.fabiohbarbosa.notifier.slack.web.model.SlackRequest;
import com.wordpress.fabiohbarbosa.notifier.slack.web.model.SlackRequestFixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SlackRequestAdapterTest extends PrepareFactoryTests {

    @Spy
    @InjectMocks
    private SlackRequestAdapter adapter;

    //~-- createScmBranchField
    @Test
    public void createScmBranchFieldGitHubTest() {
        final Scm scm = ScmFixture.newScmGithub();
        assertTrue(adapter.createScmBranchField(scm).getValue().contains("http"));
        assertTrue(adapter.createScmBranchField(scm).getValue().contains("tree"));
    }

    @Test
    public void createScmBranchFieldBitbucketTest() {
        final Scm scm = ScmFixture.newScmBitbucket();
        assertTrue(adapter.createScmBranchField(scm).getValue().contains("http"));
        assertTrue(adapter.createScmBranchField(scm).getValue().contains("src"));
    }

    @Test
    public void whenUrlNullReturnBranchName() {
        final Scm scm = ScmFixture.newScmGithub();
        scm.setUrl(null);
        assertFalse(adapter.createScmBranchField(scm).getValue().contains("http"));
    }

    @Test
    public void nullForNullBranch() {
        final Scm scm = ScmFixture.newScmGithub();
        scm.setBranch(null);
        assertNull(adapter.createScmBranchField(scm));
    }

    @Test
    public void nullForEmptyBranch() {
        final Scm scm = ScmFixture.newScmGithub();
        scm.setBranch("");
        assertNull(adapter.createScmBranchField(scm));
    }

    //~-- createScmBranchField
    @Test
    public void createScmCommitFieldGitHubTest() {
        final Scm scm = ScmFixture.newScmGithub();
        assertTrue(adapter.createScmCommitField(scm).getValue().contains("http"));
        assertTrue(adapter.createScmCommitField(scm).getValue().contains("commit/"));
    }

    @Test
    public void createScmCommitFieldBitbucketTest() {
        final Scm scm = ScmFixture.newScmBitbucket();
        assertTrue(adapter.createScmCommitField(scm).getValue().contains("http"));
        assertTrue(adapter.createScmCommitField(scm).getValue().contains("commits/"));
    }


    @Test
    public void whenUrlNullReturnCommitCode() {
        final Scm scm = ScmFixture.newScmGithub();
        scm.setUrl(null);
        assertFalse(adapter.createScmCommitField(scm).getValue().contains("http"));
    }

    @Test
    public void nullForNullCommit() {
        final Scm scm = ScmFixture.newScmGithub();
        scm.setCommit(null);
        assertNull(adapter.createScmCommitField(scm));
    }

    @Test
    public void nullForEmptyCommit() {
        final Scm scm = ScmFixture.newScmGithub();
        scm.setCommit("");
        assertNull(adapter.createScmCommitField(scm));
    }

    //~-- adapterScm
    @Test
    public void adapterScmTest() {
        final SlackRequest request = SlackRequestFixture.newSlackRequest();
        final Scm scm = new Scm();
        scm.setUrl("https://github.com/fabiohbarbosa/slack-sonar-notifier");
        adapter.adapterScm(request, scm);
    }

}