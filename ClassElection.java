import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

public class ClassElection {
    public static void main(String[] args) {
        Random random = new Random();
        int totalVotesAllowed = random.nextInt(100);
        Election election = new Election(totalVotesAllowed);
        LinkedList<String> animeNames = new LinkedList<>();
        animeNames.add("Naruto");
        animeNames.add("Sasuke");
        animeNames.add("Luffy");
        animeNames.add("Goku");
        animeNames.add("Vegeta");
        animeNames.add("Ichigo");
        animeNames.add("Eren");
        animeNames.add("Levi");
        animeNames.add("Saitama");
        animeNames.add("Edward");
        animeNames.add("Gon");
        animeNames.add("Killua");
        animeNames.add("Natsu");
        animeNames.add("Lelouch");
        animeNames.add("Light");
        animeNames.add("Gintoki");
        animeNames.add("Tanjiro");
        animeNames.add("Zenitsu");
        animeNames.add("Inosuke");
        animeNames.add("Kirito");

        int numCandidates = random.nextInt(animeNames.size()) + 1;
        Set<String> selectedCandidates = new HashSet<>();
        LinkedList<String> candidates = new LinkedList<>();
        while (selectedCandidates.size() < numCandidates) {
            int index = random.nextInt(animeNames.size());
            String candidate = animeNames.get(index);
            if (!selectedCandidates.contains(candidate)) {
                selectedCandidates.add(candidate);
                candidates.add(candidate);
            }
        }

        election.registerCandidates(candidates);

        System.out.println(" votes: ");
        for (int i = 0; i < totalVotesAllowed; i++) {
            election.addRandomVote();
        }
        election.assessElection();

        election.manipulateElection(candidates.get(random.nextInt(candidates.size())));

        System.out.println("WINNERS");
        election.retrieveTopCandidates(3);
    }
}

