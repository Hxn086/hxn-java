public class Feedback {

    private String firstName;
    private String lastName;
    private String email;
    private String completeFeedback;
    private String reviewID;
    private boolean longFeedback;

    public Feedback(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void analyseFeedback(boolean isConcatenation,
                                String sent1, String sent2, String sent3, String sent4, String sent5) {
        if (isConcatenation) {
            completeFeedback = feedbackUsingConcatenation(sent1, sent2, sent3, sent4, sent5);
        } else {
            completeFeedback = feedbackUsingStringBuilder(sent1, sent2, sent3, sent4, sent5);
        }

        checkFeedbackLength(completeFeedback);

        createReviewID(firstName, lastName, completeFeedback);
    }

    private String feedbackUsingConcatenation(String s1, String s2, String s3, String s4, String s5) {
        String concatenatedFeedback = s1 + s2 + s3 + s4 + s5;
        return concatenatedFeedback;
    }

    private String feedbackUsingStringBuilder(String s1, String s2, String s3, String s4, String s5) {
        StringBuilder sb = new StringBuilder();
        sb.append(s1);
        sb.append(s2);
        sb.append(s3);
        sb.append(s4);
        sb.append(s5);
        return sb.toString();
    }

    private void checkFeedbackLength(String feedback) {
        longFeedback = feedback.length() > 500;
    }

    private void createReviewID(String firstName, String lastName, String completeFeedback) {
    
        String fullName = firstName + lastName;
        String namePart = "";
        if (fullName.length() >= 6) {
            namePart = fullName.substring(2, 6).toUpperCase();
        } else {
            namePart = fullName.toUpperCase();
        }

        String feedbackPart = "";
        if (completeFeedback.length() >= 15) {
            feedbackPart = completeFeedback.substring(10, 15).toLowerCase();
        } else {
            feedbackPart = completeFeedback.toLowerCase();
        }

        long timestamp = System.currentTimeMillis();
        String tempID = namePart + feedbackPart + completeFeedback.length() + "_" + timestamp;

        reviewID = tempID.replace(" ", "");
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", completeFeedback='" + completeFeedback + '\'' +
                ", longFeedback=" + longFeedback +
                ", reviewID='" + reviewID + '\'' +
                '}';
    }

    public static void main(String[] args) {
        String sent1 = "I was very satisfied with the service.";
        String sent2 = "The e-Bike is quite comfortable to ride.";
        String sent3 = "The battery life of the e-Bike is impressive.";
        String sent4 = "The customer support was helpful and responsive.";
        String sent5 = "I would recommend this e-Bike to my friends and family.";

        Feedback feedback = new Feedback("Xiaonan", "Hua", "2376257072@qq.com");

        feedback.analyseFeedback(true, sent1, sent2, sent3, sent4, sent5);

        System.out.println(feedback);
    }
}
