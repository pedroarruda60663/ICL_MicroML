package target;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class BasicBlock {

	List<Object> instructions; // Changed to List<Object> to hold both Instructions and Labels

	public BasicBlock() {
		instructions = new ArrayList<>();
		addLabel(new Label());
	}

	public void addInstruction(Instruction i) {
		instructions.add(i);
	}

	public void addLabel(Label label) {
		instructions.add(label + ":");
	}

	@Override
	public String toString() {
		StringBuilder block = new StringBuilder(instructions.size() * 5);
		for (Object obj : instructions) {
			block.append(obj.toString() + "\n");
		}
		return block.toString();
	}

	public StringBuilder build() {
		StringBuilder block = new StringBuilder(instructions.size() * 5);
		build(block);
		return block;
	}

	public void build(StringBuilder sb) {
		for (Object obj : instructions) {
			sb.append(obj.toString() + "\n");
		}
	}
}

