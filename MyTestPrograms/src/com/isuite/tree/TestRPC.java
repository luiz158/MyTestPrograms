package com.isuite.tree;

import java.util.ArrayList;
import java.util.List;

public class TestRPC {

	public static void main(String[] args) {

		List<Node> list = new TestRPC().getTreeData();
		for (Node node : list) {
			System.out.print(node);
		}
	}

	public List<Node> getTreeData() {
		int[] count = { 2, 3, 4, 1, 1, 4, 1 };
		List<Node> list = new ArrayList<Node>();
		int level = 1;
		for (int i = 0; i <= count.length - 1; i++) {
			Node parent = new Node("node [" + (i + 1) + "]", level);
			List<Node> children = getChildrens(parent, count[i], 1);
			parent.setChildren(children);
			list.add(parent);
		}
		return list;
	}

	private List<Node> getChildrens(Node parentNode, int countOfChildrens,
			int level) {
		List<Node> children = null;
		for (int j = 0; j < countOfChildrens; j++) {
			if (children == null) {
				children = new ArrayList<Node>();
			}
			Node child = new Node(parentNode.getLabel() + "[" + (j + 1) + "]",
					parentNode.getLevel() + 1);
			List<Node> childChildren = getChildrens(child,
					countOfChildrens - 1, parentNode.getLevel() + 1);
			child.setChildren(childChildren);
			children.add(child);
		}
		return children;
	}
}
