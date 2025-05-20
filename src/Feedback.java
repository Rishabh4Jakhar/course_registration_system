public class Feedback<T> {
    private final String courseId;
    private T feedback;

    public Feedback(String courseId, T feedback) {
        this.courseId = courseId;
        this.feedback = feedback;
    }

    public String getCourseId() {
        return courseId;
    }

    public T getFeedback() {
        return feedback;
    }

    public void setFeedback(T feedback) {
        this.feedback = feedback;
    }
}