package test

import model.Candidate
import model.CandidateDAO
import org.junit.Test

import static org.junit.Assert.*

class TestCandidate {

    @Test
    void testListCandidate() {
        CandidateDAO candidateDAO = new CandidateDAO()
        if (candidateDAO.getCandidates().size() > 0) {
            assertTrue(true)
        } else {
            fail()
        }
    }

    @Test
    void testNewCandidate() {
        CandidateDAO candidateDAO = new CandidateDAO()

        candidateDAO.getCandidates().clear()
        candidateDAO.saveCandidate("Victor", "victor@gmail.com", "12312312312", 23, "CE", "320151000", "bla bla bla", "groovy, junit")

        List<Candidate> candidates = candidateDAO.getCandidates();
        Candidate firstCandidate = candidates.get(0);

        assertEquals(1, candidateDAO.getCandidates().size())

        assertEquals("Victor", firstCandidate.getName());
        assertEquals("victor@gmail.com", firstCandidate.getEmail());
        assertEquals("12312312312", firstCandidate.getCpf());
        assertEquals(23, firstCandidate.getAge());
        assertEquals("CE", firstCandidate.getState());
        assertEquals("320151000", firstCandidate.getCep());
        assertEquals("bla bla bla", firstCandidate.getPersonalDescription());
        assertEquals("groovy, junit", firstCandidate.getSkills().get(0));
    }
}


