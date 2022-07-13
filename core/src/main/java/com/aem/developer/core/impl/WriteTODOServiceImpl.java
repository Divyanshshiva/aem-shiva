package com.aem.developer.core.impl;


import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.developer.core.service.ResourceResolverService;
import com.aem.developer.core.service.WriteTODOService;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.Objects;
import com.aem.developer.core.constans.*;



public class WriteTODOServiceImpl  implements WriteTODOService
{
	private static final String TAG = WriteTODOServiceImpl.class.getSimpleName();
    private static final Logger LOGGER = LoggerFactory.getLogger(WriteTODOServiceImpl.class);

    private static final String USER_ID = "userId";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String COMPLETED = "completed";
    private static final String TODO_DATA_NODE = "aemdeveloper/data/todo";
	private static final String FORWARD_SLASH = "/";

    @Reference
    ResourceResolverService resourceResolverService;

	@Override
	public void writeData(String todoData)
	{
		// TODO Auto-generated method stub
		// Check if data is not empty
        if (todoData != null && !todoData.isEmpty()) {
            // Get instance of ResourceResolver
            ResourceResolver resourceResolver = resourceResolverService.getResourceResolver();
            // Adapt this resource resolver to JCR session object
            Session session = resourceResolver.adaptTo(Session.class);
            // Get the reference of the node where we want to store data
            try {
                if (session != null) {
                    // Get the reference of the root node
                    Node node = session.getRootNode();
                    if (!session.nodeExists(FORWARD_SLASH + TODO_DATA_NODE)) {
                        String[] nodePaths = TODO_DATA_NODE.split(FORWARD_SLASH);
                        for (String path : nodePaths) {
                            node = node.addNode(path);
                            session.save();
                        }
                    } else {
                        node = node.getNode(FORWARD_SLASH + TODO_DATA_NODE);
                    }
                    JSONArray jsonArray = new JSONArray(todoData);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Node currentNode;
                        if (!Objects.requireNonNull(node).hasNode("todo-" + jsonObject.getInt(ID))) {
                            currentNode = Objects.requireNonNull(node).addNode("todo-" + jsonObject.getInt(ID));
                            Objects.requireNonNull(currentNode).setProperty(USER_ID, jsonObject.getInt(USER_ID));
                            currentNode.setProperty(ID, jsonObject.getInt(ID));
                            currentNode.setProperty(TITLE, jsonObject.getString(TITLE));
                            currentNode.setProperty(COMPLETED, jsonObject.getBoolean(COMPLETED));
                        }
                    }
                    session.save();
                }
            } catch (RepositoryException | JSONException e) {
                LOGGER.error("{}: Exception occurred: {}", TAG, e.getMessage());
            }
        } else {
            LOGGER.error("{}: No data to be saved in the repository", TAG);
        }
		
	}

    
}
