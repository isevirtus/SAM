angular.module("sam").controller('NetworkController', function ($scope, $state, $stateParams, NetworkFactory) {
    var cy;
    var layoutPadding = 50;
    var aniDur = 500;
    var easing = 'linear';

    function convertToCyGraph(network) {
        var nodes = [];
        var edges = [];
        network.forEach(function (node) {
            var nodeAux = {'data': node};
            node.parents.forEach(function (parent) {
                var edgeAux = {
                    'data': {
                        'id': parent.node_id + '' + node.node_id,
                        'source': parent.node_id,
                        'target': node.node_id
                    }
                };
                edges.push(edgeAux);
            });
            delete nodeAux.parents;
            nodeAux.data.id = nodeAux.data.node_id;
            delete nodeAux.data.node_id;
            nodes.push(nodeAux);
        });

        return nodes.concat(edges);
    }

    $scope.createNetwork = function (idObjective) {
        NetworkFactory.queryNetwork(idObjective).then(function (result) {
            var elements = convertToCyGraph(result.data.nodes);
            createCytosCapeGraph(elements);
        }).catch(function (result) {
            console.error("Erro de requisição não tratado!");
            //TODO ver como tratar os erros das requisições.
        });
    };

    $scope.resetNetwork = function () {
        cy.stop();

        cy.animation({
            fit: {
                eles: cy.elements(),
                padding: layoutPadding
            },
            duration: aniDur,
            easing: easing
        }).play();
    };

    function createCytosCapeGraph(elements) {
        cy = cytoscape({

            layout: {
                name: 'dagre'
            },

            container: document.getElementById('cy'),
            // initial viewport state:
            style: [
                {
                    selector: 'node',
                    style: {
                        'background-color': '#666',
                        'width': '40',
                        'height': '40',
                        'text-halign': 'right',
                        'border-width': 1,
                        'text-wrap': 'wrap',
                        'content' : 'data(id)',
                        'label': 'data(name)'
                    }
                },

                {
                    selector: 'node[nodeSource = "metric"]',
                    style: {
                        'background-color': '#7e57c2',
                    }
                },

                {
                    selector: 'node[nodeSource = "question"]',
                    style: {
                        'background-color': '#00897b',
                    }
                },

                {
                    selector: 'node[nodeSource = "objective"]',
                    style: {
                        'background-color': '#5c6bc0'
                    }
                },

                {
                    selector: 'edge',
                    style: {
                        'width': 4,
                        'target-arrow-shape': 'triangle',
                        'line-color': '#9dbaea',
                        'target-arrow-color': '#9dbaea',
                        'curve-style': 'bezier'
                    }
                }
            ],

            elements: elements
        });
    }
});