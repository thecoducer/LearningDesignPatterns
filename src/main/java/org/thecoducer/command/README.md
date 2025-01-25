# Command

- **Behavioral Design Pattern**
- It encapsulates a request as an object, allowing you to parameterize objects with different requests, delay or queue a request, and support undoable operations.

## Intent

The Command Pattern helps decouple the **invoker** (the object that makes the request) from the **receiver** (the object that performs the action). It is particularly useful when you need to:
- Encapsulate requests or actions as objects.
- Support undo/redo operations.
- Allow requests to be queued, logged, or executed at different times.

## Structure
```
Client --> Invoker ---> Command Interface --> ConcreteCommand --> Receiver
```


