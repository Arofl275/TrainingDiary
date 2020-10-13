package ap.trainingdiary.gui;

import java.awt.event.MouseListener;

import java.io.File;
import java.io.IOException;

import java.util.Iterator;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import javax.swing.JTree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;



import ap.trainingdiary.type.AbctractComparable;
import ap.trainingdiary.type.AbstractIndexNodeUserObject;
import ap.trainingdiary.type.CommentNodeUserObject;
import ap.trainingdiary.type.CommentsNodeUserObject;
import ap.trainingdiary.type.CommentTemplatePair;
import ap.trainingdiary.type.ComparableTreeNode;
import ap.trainingdiary.type.DayNodeUserObject;
import ap.trainingdiary.type.MonthNodeUserObject;
import ap.trainingdiary.type.NodeType;
import ap.trainingdiary.type.RootNodeUserObject;
import ap.trainingdiary.type.TemplateNodeUserObject;
import ap.trainingdiary.type.TemplatesNodeUserObject;
import ap.trainingdiary.type.TrainingNodeUserObject;
import ap.trainingdiary.type.TrainingsNodeUserObject;
import ap.trainingdiary.type.YearNodeUserObject;

import ap.trainingdiary.type.TrainingPath;

public class TrainingDiaryTree{
	private final DefaultMutableTreeNode commentsNode;
	private final DefaultMutableTreeNode templatesNode;
	private final DefaultMutableTreeNode trainingsNode;
	private final TreeSet<ComparableTreeNode> indelibleNodes;
	private final DefaultTreeModel model;
	private final JTree tree;
	private final TreeSelectionModel selectionModel;
	public TrainingDiaryTree(){
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new RootNodeUserObject());
		commentsNode = new DefaultMutableTreeNode(new CommentsNodeUserObject());
		templatesNode = new DefaultMutableTreeNode(new TemplatesNodeUserObject());
		trainingsNode = new DefaultMutableTreeNode(new TrainingsNodeUserObject());

		indelibleNodes = new TreeSet<ComparableTreeNode>();
		indelibleNodes.add(new ComparableTreeNode(root));
		indelibleNodes.add(new ComparableTreeNode(commentsNode));
		indelibleNodes.add(new ComparableTreeNode(templatesNode));
		indelibleNodes.add(new ComparableTreeNode(trainingsNode));
		
		model = new DefaultTreeModel(root, true);
		tree = new JTree(model);
		selectionModel = new DefaultTreeSelectionModel();
		selectionModel.setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
		tree.setSelectionModel(selectionModel);
		model.insertNodeInto(trainingsNode, root, 0);
		model.insertNodeInto(templatesNode, root, 1);
		model.insertNodeInto(commentsNode, root, 2);
	}
	public JTree getTree(){
		return tree;
	}
	private void removeComment(final DefaultMutableTreeNode node){
		final CommentNodeUserObject comment = (CommentNodeUserObject) node.getUserObject();
		File file = comment.getFile();
		if(file != null){
			try{
				file.delete();
			}
			catch(SecurityException se){
				se.printStackTrace();
			}
		}
		model.removeNodeFromParent(node);
	}
	private void removeTemplate(final DefaultMutableTreeNode node){
		final TemplateNodeUserObject template = (TemplateNodeUserObject) node.getUserObject();
		File file = template.getFile();
		if(file != null){
			try{
				file.delete();
			}
			catch(SecurityException se){
				se.printStackTrace();
			}
		}
		model.removeNodeFromParent(node);
	}
	private void removeTraining(final DefaultMutableTreeNode node){
		final TrainingNodeUserObject training = (TrainingNodeUserObject) node.getUserObject();
		File file = training.getFile();
		if(file != null){
			try{
				file.delete();
			}
			catch(SecurityException se){
				se.printStackTrace();
			}
		}
		model.removeNodeFromParent(node);
	}
	private void removeNode(final DefaultMutableTreeNode node){
		if(model.getChildCount(node) == 0){
			model.removeNodeFromParent(node);
		}
	}
	public void removeAllSelectedNodes(){
		if(tree.getSelectionCount() > 0){
			final TreeSet<ComparableTreeNode> set = new TreeSet<ComparableTreeNode>();
			final TreePath[] selectedPaths = tree.getSelectionPaths();
			for(int i = 0; i < selectedPaths.length; i++){
				final Object[] nodes = selectedPaths[i].getPath();
				final int last = nodes.length - 1;
				for(int j = 0; j < last; j++){
					ComparableTreeNode cNode = new ComparableTreeNode((DefaultMutableTreeNode) nodes[j]);
					addIfIndelible(set, cNode);
				}
				final DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodes[last];
				final ComparableTreeNode cNode = new ComparableTreeNode(node);
				addIfIndelible(set, cNode);
				addChilderenIfNotList(set, cNode);
			}
			DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
			for(Iterator<ComparableTreeNode> it = set.iterator(); it.hasNext();){
				final ComparableTreeNode cNode = it.next();
				if(cNode.getNodeType() == NodeType.COMMENTNODEUSEROBJECT){
					removeComment(cNode.getNode());
				}
				else if(cNode.getNodeType() == NodeType.TEMPLATENODEUSEROBJECT){
					removeTemplate(cNode.getNode());
				}
				else if(cNode.getNodeType() == NodeType.TRAININGNODEUSEROBJECT){
					removeTraining(cNode.getNode());
				}
				else{
					removeNode(cNode.getNode());
				}
			}
		}
	}
	private void addChilderenIfNotList(final TreeSet<ComparableTreeNode> source, final ComparableTreeNode cNode){
		if(
			cNode.getNodeType() != NodeType.COMMENTNODEUSEROBJECT &&
			cNode.getNodeType() != NodeType.TEMPLATENODEUSEROBJECT &&
			cNode.getNodeType() != NodeType.TRAININGNODEUSEROBJECT
		){
			addChildrenToSet(source, cNode.getNode());
		}
	}
	private void addIfIndelible(final TreeSet<ComparableTreeNode> source, final ComparableTreeNode cNode){
		if(!indelibleNodes.contains(cNode)){
			source.add(cNode);
		}
	}
	private void addChildrenToSet(final TreeSet<ComparableTreeNode> set, final DefaultMutableTreeNode node){
		for(int i = 0; i < node.getChildCount(); i++){
			final DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
			final ComparableTreeNode cNode = new ComparableTreeNode(child);
			addIfIndelible(set, cNode);
			addChilderenIfNotList(set, cNode);
		}
	}
	private DefaultMutableTreeNode insertNewNode(final DefaultMutableTreeNode node, final AbctractComparable abctractComparable, final boolean isNode, final int index){
		final DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(abctractComparable, isNode);
		model.insertNodeInto(newNode, node, index);
		return newNode;		
	}
	private DefaultMutableTreeNode addAbctractComparable(final DefaultMutableTreeNode node, final AbctractComparable abctractComparable, final boolean isNode){
		int childCount = node.getChildCount();
		if(childCount == 0){
			// System.out.println(String.format("No Children. %s", abctractComparable));
			return insertNewNode(node, abctractComparable, isNode, 0);
		}
		else{
			for(int index = 0; index < childCount; index++){
				final DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) model.getChild(node, index);
				final AbctractComparable abctractComparableOfNode = (AbctractComparable) childNode.getUserObject();
				final int cmp = abctractComparable.compareTo(abctractComparableOfNode);
				if(cmp == 0){
					// System.out.println(String.format("%s == %s", abctractComparable, abctractComparableOfNode));
					if(abctractComparable.getNodeType() == NodeType.COMMENTNODEUSEROBJECT){
						removeComment(childNode);
						return insertNewNode(node, abctractComparable, isNode, index);
					}
					else if(abctractComparable.getNodeType() == NodeType.TEMPLATENODEUSEROBJECT){
						removeTemplate(childNode);
						return insertNewNode(node, abctractComparable, isNode, index);
					}
					else if(abctractComparable.getNodeType() == NodeType.TRAININGNODEUSEROBJECT){
						removeTraining(childNode);
						return insertNewNode(node, abctractComparable, isNode, index);
					}
					else{
						return childNode;
					}
				}
				else if(cmp < 0){
					// System.out.println(String.format("%s < %s", abctractComparable, abctractComparableOfNode));
					return insertNewNode(node, abctractComparable, isNode, index);
				}
			}
			// System.out.println(String.format("%s > all", abctractComparable));
			return insertNewNode(node, abctractComparable, isNode, childCount);
		}
	}
	private DefaultMutableTreeNode addYear(final YearNodeUserObject year){
		return addAbctractComparable(trainingsNode, year, true);
	}
	private DefaultMutableTreeNode addMonth(final DefaultMutableTreeNode node, final MonthNodeUserObject month){
		return addAbctractComparable(node, month, true);
	}
	private DefaultMutableTreeNode addDay(final DefaultMutableTreeNode node, final DayNodeUserObject day){
		return addAbctractComparable(node, day, true);
	}
	private DefaultMutableTreeNode addTraining(final DefaultMutableTreeNode node, final TrainingNodeUserObject training){
		return addAbctractComparable(node, training, false);
	}
	public DefaultMutableTreeNode addComment(
		final String nodeName,
		final File file
	){
		return addAbctractComparable(commentsNode, new CommentNodeUserObject(nodeName, file), false);
	}
	public DefaultMutableTreeNode addTraining(
		final int year,
		final int month,
		final int day,
		final int training,
		final File file
	){
		DefaultMutableTreeNode yearNode = addYear(new YearNodeUserObject(year));
		DefaultMutableTreeNode monthNode = addMonth(yearNode, new MonthNodeUserObject(month));
		DefaultMutableTreeNode dayNode = addDay(monthNode, new DayNodeUserObject(day));
		DefaultMutableTreeNode trainingNode = addTraining(dayNode, new TrainingNodeUserObject(training, file));
		return trainingNode;
	}
	public DefaultMutableTreeNode addTemplate(
		final String nodeName,
		final File file
	){
		return addAbctractComparable(templatesNode, new TemplateNodeUserObject(nodeName, file), false);
	}
	public boolean  isSelectionEmpty(){
		return tree.isSelectionEmpty();
	}
	public boolean isAddTrainingCondition(){
		if(isSelectionEmpty()){
			return true;
		}
		else if(tree.getSelectionModel().getSelectionCount() <= 2){
			int commentCounter = 0;
			int templateCounter = 0;
			final TreePath[] selectedPaths = tree.getSelectionPaths();
			for(int i = 0; i < selectedPaths.length; i++){
				final TreePath selectedPath = selectedPaths[i];
				final Object[] nodes = selectedPath.getPath();
				final DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodes[nodes.length - 1];
				final AbctractComparable abctractComparable = (AbctractComparable) node.getUserObject();
				if(abctractComparable.getNodeType() == NodeType.COMMENTNODEUSEROBJECT){
					commentCounter++;
				}
				else if(abctractComparable.getNodeType() == NodeType.TEMPLATENODEUSEROBJECT){
					templateCounter++;
				}
				else{
					return false;
				}
			}
			if(commentCounter < 2 && templateCounter < 2) return true;
		}
		return false;
	}
	public boolean isSingleListSelected(){
		if(tree.getSelectionModel().getSelectionCount() == 1){
			final TreePath[] selectedPaths = tree.getSelectionPaths();
			final TreePath selectedPath = selectedPaths[0];
			final Object[] nodes = selectedPath.getPath();
			final DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodes[nodes.length - 1];
			final AbctractComparable abctractComparable = (AbctractComparable) node.getUserObject();
			if(
				abctractComparable.getNodeType() == NodeType.COMMENTNODEUSEROBJECT ||
				abctractComparable.getNodeType() == NodeType.TRAININGNODEUSEROBJECT ||
				abctractComparable.getNodeType() == NodeType.TEMPLATENODEUSEROBJECT
			){
				return true;
			}
		}
		return false;
	}
	public NodeType getNodeTypeOfSingleList(){
		if(tree.getSelectionModel().getSelectionCount() == 1){
			final TreePath[] selectedPaths = tree.getSelectionPaths();
			final TreePath selectedPath = selectedPaths[0];
			final Object[] nodes = selectedPath.getPath();
			final DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodes[nodes.length - 1];
			final AbctractComparable abctractComparable = (AbctractComparable) node.getUserObject();
			if(
				abctractComparable.getNodeType() == NodeType.COMMENTNODEUSEROBJECT ||
				abctractComparable.getNodeType() == NodeType.TRAININGNODEUSEROBJECT ||
				abctractComparable.getNodeType() == NodeType.TEMPLATENODEUSEROBJECT
			){
				return abctractComparable.getNodeType();
			}
		}
		return null;
	}
	public CommentNodeUserObject getCommentNodeUserObject(){
		if(tree.getSelectionModel().getSelectionCount() == 1){
			final TreePath[] selectedPaths = tree.getSelectionPaths();
			final TreePath selectedPath = selectedPaths[0];
			final Object[] nodes = selectedPath.getPath();
			final DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodes[nodes.length - 1];
			final AbctractComparable abctractComparable = (AbctractComparable) node.getUserObject();
			if(abctractComparable.getNodeType() == NodeType.COMMENTNODEUSEROBJECT){
				return (CommentNodeUserObject) abctractComparable;
			}
		}
		return null;
	}
	public CommentTemplatePair getCommentTemplatePair(){
		final int count = tree.getSelectionModel().getSelectionCount();
		if(count == 1 || count == 2){
			CommentNodeUserObject cnuo = null;
			TemplateNodeUserObject tnuo = null;

			final TreePath[] selectedPaths = tree.getSelectionPaths();
			for(TreePath path: selectedPaths){
				final Object[] nodes = path.getPath();
				final DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodes[nodes.length - 1];
				final AbctractComparable abctractComparable = (AbctractComparable) node.getUserObject();
				if(abctractComparable.getNodeType() == NodeType.COMMENTNODEUSEROBJECT){
					cnuo = (CommentNodeUserObject) abctractComparable;
				}
				else if(abctractComparable.getNodeType() == NodeType.TEMPLATENODEUSEROBJECT){
					tnuo = (TemplateNodeUserObject) abctractComparable;
				}
				else{
					return null;
				}
			}
			return new CommentTemplatePair(cnuo, tnuo);
		}
		return null;
	}
	public TemplateNodeUserObject getTemplateNodeUserObject(){
		if(tree.getSelectionModel().getSelectionCount() == 1){
			final TreePath[] selectedPaths = tree.getSelectionPaths();
			final TreePath selectedPath = selectedPaths[0];
			final Object[] nodes = selectedPath.getPath();
			final DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodes[nodes.length - 1];
			final AbctractComparable abctractComparable = (AbctractComparable) node.getUserObject();
			if(abctractComparable.getNodeType() == NodeType.TEMPLATENODEUSEROBJECT){
				return (TemplateNodeUserObject) abctractComparable;
			}
		}
		return null;
	}
	public TrainingNodeUserObject getTrainingNodeUserObject(){
		if(tree.getSelectionModel().getSelectionCount() == 1){
			final TreePath[] selectedPaths = tree.getSelectionPaths();
			final TreePath selectedPath = selectedPaths[0];
			final Object[] nodes = selectedPath.getPath();
			final DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodes[nodes.length - 1];
			final AbctractComparable abctractComparable = (AbctractComparable) node.getUserObject();
			if(abctractComparable.getNodeType() == NodeType.TRAININGNODEUSEROBJECT){
				return (TrainingNodeUserObject) abctractComparable;
			}
		}
		return null;
	}
	public void clearSelection(){
		selectionModel.clearSelection();
	}
	public void addMouseListener(final MouseListener mouseListener){
		tree.addMouseListener(mouseListener);
	}
	private void addToTrainingPathCollection(final Collection<TrainingPath> collection, final DefaultMutableTreeNode node, final int year, final int month, final int day){
		int childCount = node.getChildCount();
		for(int index = 0; index < childCount; index++){
			final DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) model.getChild(node, index);
			final AbstractIndexNodeUserObject abstractIndexNodeUserObject = (AbstractIndexNodeUserObject) childNode.getUserObject();
			final int training = abstractIndexNodeUserObject.getIndex();
			// System.out.println(String.format("Year: %d, Month: %d, Day: %d, Training: %d", year, month, day, training));
			collection.add(new TrainingPath(year, month, day, training));
		}
	}
	private void addToTrainingPathCollection(final Collection<TrainingPath> collection, final DefaultMutableTreeNode node, final int year, final int month){
		int childCount = node.getChildCount();
		for(int index = 0; index < childCount; index++){
			final DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) model.getChild(node, index);
			final AbstractIndexNodeUserObject abstractIndexNodeUserObject = (AbstractIndexNodeUserObject) childNode.getUserObject();
			final int day = abstractIndexNodeUserObject.getIndex();
			addToTrainingPathCollection(collection, childNode, year, month, day);
		}
	}
	private void addToTrainingPathCollection(final Collection<TrainingPath> collection, final DefaultMutableTreeNode node, final int year){
		int childCount = node.getChildCount();
		for(int index = 0; index < childCount; index++){
			final DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) model.getChild(node, index);
			final AbstractIndexNodeUserObject abstractIndexNodeUserObject = (AbstractIndexNodeUserObject) childNode.getUserObject();
			final int month = abstractIndexNodeUserObject.getIndex();
			addToTrainingPathCollection(collection, childNode, year, month);
		}
	}
	public Collection<TrainingPath> getTrainingPaths(){
		Collection<TrainingPath> collection = new LinkedList<TrainingPath>();
		int childCount = trainingsNode.getChildCount();
		for(int index = 0; index < childCount; index++){
			final DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) model.getChild(trainingsNode, index);
			final AbstractIndexNodeUserObject abstractIndexNodeUserObject = (AbstractIndexNodeUserObject) childNode.getUserObject();
			final int year = abstractIndexNodeUserObject.getIndex();
			addToTrainingPathCollection(collection, childNode, year);
		}
		return collection;
	}
	public Collection<String> getCommentNames(){
		Collection<String> collection = new LinkedList<String>();
		int childCount = commentsNode.getChildCount();
		for(int index = 0; index < childCount; index++){
			final DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) model.getChild(commentsNode, index);
			final CommentNodeUserObject commentNodeUserObject = (CommentNodeUserObject) childNode.getUserObject();
			final String commentName = commentNodeUserObject.getCommentName();
			collection.add(commentName);
		}
		return collection;
	}
	public Collection<String> getTemplateNames(){
		Collection<String> collection = new LinkedList<String>();
		int childCount = templatesNode.getChildCount();
		for(int index = 0; index < childCount; index++){
			final DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) model.getChild(templatesNode, index);
			final TemplateNodeUserObject templateNodeUserObject = (TemplateNodeUserObject) childNode.getUserObject();
			final String templateName = templateNodeUserObject.getTemplateName();
			collection.add(templateName);
		}
		return collection;
	}
}
