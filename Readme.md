## MVC Framework

[Following Dr. Pearce's lecture with slight modifications](http://www.cs.sjsu.edu/faculty/pearce/modules/lectures/ood2/mvc/index.htm)

Model-View-Controller (MVC) architectures dominate desktop and web-based applications. Operating under the principle of avoiding mixed domain coupling, specifically, the principle:

> `Application logic should be independent of presentation logic.`

The MVC architecture does this by enforcing a strict one-way dependency between less-reusable presentation logic and more-reusable application logic:

The MVC architecture is a refinement of the Entity-Controller-Boundary Pattern used in analysis models.

Strictly speaking, model, view, and controller are roles played by multiple classes, not single classes.

The model belongs to the application package. It encapsulates business logic, rules, entities, processes, etc.

More often, the model is a collection of related entity classes.

```
	Policy, Claim, PolicyHolder ...
	
	Treatment, Patient, Test ...
	
	Auction, Bidder, Bid, Item ...
	
	Dungeon, Weapon, Character ...
	
	etc.
```

Views and controllers (there may be many) belong to the presentation package.

A view might be a web page or a desktop window. Instantiating the Composite Design Pattern, a desktop window often contains child-windows and controls.

A controller listens for user input coming through the view's controls (buttons, sliders, menu items. etc.). When input arrives, the controller updates the model. This may involve calling many model methods in a carefully prescribed order.

Often controllers correspond to use cases from the analysis model.

### Problem: How do views know when to query the model?

Answer: Publisher-Subscriber Pattern

### Problem: How can commands be decoupled from the controller?

Answer: Command Processor Pattern

### Problem: How can undo/redo be implemented without violating encapsulation?

Answer: The Memento Pattern

### Problem: How can features be added to view without modifying the view?

Answer: The Decorator Pattern


## My modifications (Changelog):
* Modified how the memento is made from the model. My method uses reflection to build a memento from the model. Using this it is possible to hide the complexities from the user of the framework.
  Also the framework takes care of the main execution workflow on its own. The user just needs to provide information of the execution logic.
  
* The `Command` now doesn't have subclasses, rather it has a part called `ExecuteLogic` that holds the execution logic within which is called when the command is executed.
  This way the user doesn't have to write keep making subclasses for each command for just mentioning the logic. Emphasis on the `code as data` principle.
  Although this might go against the Object Oriented Design methodology, I find it to be of more useful in this framework.
  
* `ExecuteLogic` is a functional interface in Java, makes writing the executionlogic easier using lambda expressions.

* Also added a `paramMap` in the `Command` for holding additional information needed for the command to execute. The idea for this came to me when I was implementing the DimensionView for the BrickCAD example.
  There will always be scenarios where the data needed by the exectionlogic for the command is not going to be available from the model. Hence having a paramMap is going to take care of that scenario.

* Created an `InstanceFactory` for producing singleton instances of the classes needed.