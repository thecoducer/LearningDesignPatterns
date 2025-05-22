# Command

- **Behavioral Design Pattern**
- It encapsulates a request as an object, allowing you to parameterize objects with different requests, delay or queue a request, and support undo/redo operations.
- It helps decouple the **invoker** (the object that makes the request) from the **receiver** (the object that performs the action).
- For example:
  - A GUI framework without the Command Pattern might require subclasses of buttons for each action (`SaveButton`, `PrintButton`, `UndoButton`).
  - With the Command Pattern, a generic Button class can accept any command, avoiding subclass explosion for the invoker.

```
Client --> Invoker ---> Command Interface --> ConcreteCommand --> Receiver
```

## Implementation


