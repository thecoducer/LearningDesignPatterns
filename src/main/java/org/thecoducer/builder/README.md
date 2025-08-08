# Builder Pattern

This is a creational design pattern used for constructing objects that contain numerous fields. It allows us to build an immutable object populated with exactly the fields we need.

This pattern helps us avoid the problem of constructor explosion, also known as telescoping constructors.

Builder lets you collect all the data first in a mutable builder object. Then create a fully formed immutable object. Once built, you can’t change it — which is safer in multi-threaded or complex systems.

We can have validation checks for our fields as well.


