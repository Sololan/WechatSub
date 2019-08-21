package pojo;

public class ProcessList {
    String processName;
    String processAddress;

    public ProcessList(String processName, String processAddress) {
        this.processName = processName;
        this.processAddress = processAddress;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessAddress() {
        return processAddress;
    }

    public void setProcessAddress(String processAddress) {
        this.processAddress = processAddress;
    }

    @Override
    public String toString() {
        return "ProcessList{" +
                "processName='" + processName + '\'' +
                ", processAddress='" + processAddress + '\'' +
                '}';
    }
}
