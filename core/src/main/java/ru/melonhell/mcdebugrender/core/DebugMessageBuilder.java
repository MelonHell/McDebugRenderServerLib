package ru.melonhell.mcdebugrender.core;

import ru.melonhell.mcdebugrender.core.shape.Shape;
import ru.melonhell.mcdebugrender.core.utils.MdrNamespaceID;

import java.util.ArrayList;
import java.util.List;

public abstract class DebugMessageBuilder {
    protected final List<Operation> ops = new ArrayList<>();

    public DebugMessageBuilder set(String namespaceId, Shape shape) {
        return set(MdrNamespaceID.from(namespaceId), shape);
    }

    public DebugMessageBuilder set(MdrNamespaceID id, Shape shape) {
        ops.add(new Operation.Set(id, shape));
        return this;
    }

    public DebugMessageBuilder remove(String namespaceId) {
        return remove(MdrNamespaceID.from(namespaceId));
    }

    public DebugMessageBuilder remove(MdrNamespaceID id) {
        ops.add(new Operation.Remove(id));
        return this;
    }

    public DebugMessageBuilder clear(String namespace) {
        ops.add(new Operation.ClearNS(namespace));
        return this;
    }

    public DebugMessageBuilder clear() {
        ops.add(new Operation.Clear());
        return this;
    }

    public abstract DebugMessage build();
}
