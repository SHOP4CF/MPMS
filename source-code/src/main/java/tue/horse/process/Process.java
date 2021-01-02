package tue.horse.process;

/**
 * Process definitions.
 */
public enum Process {
    CORE_PROCESS("Core_Process", "bpmn/core_process.bpmn", true);

    private String definitionKey;
    private String filePath;
    private boolean mainProcess;

    Process(String definitionKey, String filePath, boolean mainProcess) {
        this.definitionKey = definitionKey;
        this.filePath = filePath;
        this.mainProcess = mainProcess;
    }

    public final String getFilePath() {
        return filePath;
    }

    public final String getDefinitionKey() {
        return definitionKey;
    }

    public boolean isMainProcess() {
        return mainProcess;
    }

}
