package br.edu.ufcg.embedded.sam.models;

/**
 * Enum that describes the possible roles of people involved in a project.
 */
public enum Role {
    DEVELOPER("Developer"), STAKEHOLDER("Stakeholder"), TESTER("Tester"), QA("QA");

    private String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
