package model;

public class Score {
    private int success = 0;
    private int failure = 0;

    //region getter/setter
    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }
    //endregion
}
