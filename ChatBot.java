import java.util.*;

public class ChatBot {
    private static Map<String, List<String>> responses = new HashMap<>();
    private static Map<String, List<String>> keywords = new HashMap<>();

    public static void main(String[] args) {
        initBot();

        Scanner scanner = new Scanner(System.in);
        System.out.println("ChatBot: Hello! Ask me anything. Type 'exit' to quit.");

        while (true) {
            System.out.print("You: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("exit")) {
                System.out.println("ChatBot: Goodbye! Take care.");
                break;
            }

            String intent = detectIntent(input);
            respond(intent);
        }

        scanner.close();
    }

    // Initialize bot data
    private static void initBot() {
        // Greeting
        keywords.put("greeting", Arrays.asList("hi", "hello", "hey", "good morning", "good evening"));
        responses.put("greeting", Arrays.asList("Hi there!", "Hello!", "Greetings!", "Hey!"));

        // Thanks
        keywords.put("thanks", Arrays.asList("thanks", "thank you", "thx"));
        responses.put("thanks", Arrays.asList("You're welcome!", "No problem!", "Anytime!"));

        // Goodbye
        keywords.put("goodbye", Arrays.asList("bye", "see you", "goodbye", "take care"));
        responses.put("goodbye", Arrays.asList("Goodbye!", "Take care!", "See you soon!"));

        // Help
        keywords.put("help", Arrays.asList("help", "support", "assist", "how to"));
        responses.put("help", Arrays.asList("I'm here to help! What do you need?", "How can I assist you?", "What can I do for you?"));

        // Default
        responses.put("default", Arrays.asList("Sorry, I didn't understand that.", "Can you please rephrase?", "Hmm, I'm not sure about that."));
    }

    // Detect intent based on keywords
    private static String detectIntent(String input) {
        for (String intent : keywords.keySet()) {
            for (String keyword : keywords.get(intent)) {
                if (input.contains(keyword)) {
                    return intent;
                }
            }
        }
        return "default";
    }

    // Respond based on intent
    private static void respond(String intent) {
        List<String> possibleResponses = responses.getOrDefault(intent, responses.get("default"));
        String reply = possibleResponses.get(new Random().nextInt(possibleResponses.size()));
        System.out.println("ChatBot: " + reply);
    }
}