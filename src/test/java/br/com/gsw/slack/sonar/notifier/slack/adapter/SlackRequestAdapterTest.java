package br.com.gsw.slack.sonar.notifier.slack.adapter;

import br.com.gsw.slack.sonar.notifier.PrepareFactoryTests;
import br.com.gsw.slack.sonar.notifier.scm.model.Scm;
import br.com.gsw.slack.sonar.notifier.scm.model.ScmFixture;
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

}