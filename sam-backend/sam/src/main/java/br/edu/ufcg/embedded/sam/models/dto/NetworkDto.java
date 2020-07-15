package br.edu.ufcg.embedded.sam.models.dto;

import br.edu.ufcg.embedded.sam.models.bayesiannetwork.Network;

/**
 * Created by mendelssohn on 20/06/17.
 */
public class NetworkDto {
    private String status;
    private Network data;

    public Network getNetwork() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Network getData() {
        return data;
    }

    public void setData(Network data) {
        this.data = data;
    }
}
