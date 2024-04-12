import java.util.LinkedList;
import java.util.Random;

public class Election {
    LinkedList<String> candidatesList;
    int[] voteCounts;
    int totalVotesAllowed;
    int votesTallied;

    public Election(int totalVotesAllowed) {
        this.totalVotesAllowed = totalVotesAllowed;
        votesTallied = 0;
    }

    public void registerCandidates(LinkedList<String> candidates) {
        this.candidatesList = candidates;
        voteCounts = new int[candidates.size()];
        for (int i = 0; i < voteCounts.length; i++) {
            voteCounts[i] = 0;
        }
    }

    public void addVote(String selectedCandidate) {
        if (votesTallied > totalVotesAllowed) {
            return;
        }
        votesTallied++;
        for (int i = 0; i < candidatesList.size(); i++) {
            if (candidatesList.get(i).equals(selectedCandidate)) {
                voteCounts[i]++;
                break;
            }
        }
    }

    void heapify(LinkedList<String> candidates, int[] votes, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < votes.length && votes[left] > votes[largest]) {
            largest = left;
        }
        if (right < votes.length && votes[right] > votes[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = votes[i];
            String tempCandidate = candidates.get(i);
            votes[i] = votes[largest];
            candidates.set(i, candidates.get(largest));
            votes[largest] = temp;
            candidates.set(largest, tempCandidate);
            heapify(candidates, votes, largest);
        }
    }

    public void addRandomVote() {
        if (votesTallied > totalVotesAllowed) {
            return;
        }
        votesTallied++;
        Random random = new Random();
        int randomIndex = random.nextInt(candidatesList.size());
        voteCounts[randomIndex]++;
    }

    public void manipulateElection(String selectedCandidate) {
        int candidateIndex = -1;
        for (int i = 0; i < candidatesList.size(); i++) {
            if (candidatesList.get(i).equals(selectedCandidate)) {
                candidateIndex = i;
                break;
            }
        }
        if (candidateIndex == -1) {
            System.out.println("Invalid candidate");
            return;
        }
        int remainingVotes = totalVotesAllowed - votesTallied;
        voteCounts[candidateIndex] += remainingVotes;
        for (int i = voteCounts.length / 2 - 1; i >= 0; i--) {
            heapify(candidatesList, voteCounts, i);
        }
        while (!candidatesList.get(0).equals(selectedCandidate)) {
            voteCounts[0]--;
            voteCounts[candidateIndex]++;
            for (int i = voteCounts.length / 2 - 1; i >= 0; i--) {
                heapify(candidatesList, voteCounts, i);
            }
            for (int i = 0; i < candidatesList.size(); i++) {
                if (candidatesList.get(i).equals(selectedCandidate)) {
                    candidateIndex = i;
                    break;
                }
            }
        }
    }

    public void retrieveTopCandidates(int count) {
        assessElection(count);
    }

    public void assessElection(int count) {
        int[] tempVotes = new int[voteCounts.length];
        LinkedList<String> tempCandidates = new LinkedList<>();
        for (int i = 0; i < voteCounts.length; i++) {
            tempVotes[i] = voteCounts[i];
            tempCandidates.add(candidatesList.get(i));
        }

        for (int j = 0; j < count; j++) {
            for (int k = voteCounts.length / 2 - 1; k >= 0; k--) {
                heapify(candidatesList, voteCounts, k);
            }
            System.out.println(candidatesList.get(0) +":" + voteCounts[0]);
            voteCounts[0] = -1;
        }
        voteCounts = tempVotes;
        candidatesList = tempCandidates;
    }

    public void assessElection() {
        assessElection(voteCounts.length);
    }
}



