package br.edu.ufcg.embedded.sam.services.bayesiannetwork;

import br.edu.ufcg.embedded.sam.models.Metric;
import br.edu.ufcg.embedded.sam.models.Objective;
import br.edu.ufcg.embedded.sam.models.Question;
import br.edu.ufcg.embedded.sam.models.bayesiannetwork.Network;
import br.edu.ufcg.embedded.sam.models.bayesiannetwork.Node;
import br.edu.ufcg.embedded.sam.models.bayesiannetwork.ParentNode;
import br.edu.ufcg.embedded.sam.repositories.NetworkRepository;
import br.edu.ufcg.embedded.sam.repositories.NodeRepository;
import br.edu.ufcg.embedded.sam.repositories.ObjectiveRepository;
import br.edu.ufcg.embedded.sam.services.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NetworkService extends CrudService<Network> {

    private static final String NODE_SOURCE_METRIC = "metric";
    private static final String NODE_SOURCE_QUESTION = "question";
    private static final String NODE_SOURCE_OBJECTIVE = "objective";

    private NetworkRepository networkRepository;
    private NodeRepository nodeRepository;
    private ObjectiveRepository objectiveRepository;
    private NetworkApiService networkApiService;

    @Autowired
    public NetworkService(NetworkRepository networkRepository,
                          NodeRepository nodeRepository,
                          ObjectiveRepository objectiveRepository,
                          NetworkApiService networkApiService) {
        this.networkRepository = networkRepository;
        this.objectiveRepository = objectiveRepository;
        this.nodeRepository = nodeRepository;
        this.networkApiService = networkApiService;
    }

    @Transactional
    private Network saveNetWorkFromObjective(Integer idObjective) {
        Objective objective = objectiveRepository.findOne(idObjective);
        Network network = new Network();
        network.setObjective(objective);

        Node objectiveNode = new Node();
        objectiveNode.setNodeSource(NODE_SOURCE_OBJECTIVE);
        objectiveNode.setName(objective.getName());
        objectiveNode = nodeRepository.save(objectiveNode);

        network.addNode(objectiveNode);

        for (Question question : objective.getQuestions()) {
            Node questionNode = new Node();
            questionNode.setNodeSource(NODE_SOURCE_QUESTION);
            questionNode.setName(question.getQuestion());

            ParentNode questionParent = new ParentNode();
            questionParent.setNode_id(objectiveNode.getNode_id());
            questionParent.setName(objective.getName());
            questionNode.addParent(questionParent);

            nodeRepository.save(questionNode);
            network.addNode(questionNode);

            for (Metric metric : question.getMetrics()) {
                Node metricNode = new Node();
                metricNode = nodeRepository.save(metricNode);
                metricNode.setNodeSource(NODE_SOURCE_METRIC);
                metricNode.setName(metric.getName());

                ParentNode metricParent = new ParentNode();
                metricParent.setName(questionNode.getName());
                metricParent.setNode_id(questionNode.getNode_id());

                metricNode.addParent(metricParent);
                network.addNode(metricNode);
            }
        }

        network = networkRepository.save(network);

        return network;
    }

    @Override
    protected JpaRepository<Network, Integer> getRepository() {
        return networkRepository;
    }

    public Network queryByObjective(Integer idObjective) {
        Network network = networkRepository.findByObjectiveId(idObjective);
        if (network == null) {
            network = saveNetWorkFromObjective(idObjective);
        }
        return networkApiService.queryForNetwork(network);
    }
}
