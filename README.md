# draggable-card-compose

### Step 1. Add the JitPack repository to your build file

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	
### Step 2. Add the dependency

dependencies {
	        implementation 'com.github.outcode-aashutosh:draggable-card-compose:1.0.0'
}


### Usage

Import DraggableCard class anywhere in your project.

DraggableCard(
    item: Any, //Data Class of Item
    modifier: Modifier = Modifier, //Custom Modifier Field
    onSwiped: (Any, Any) -> Unit, //Handle Swiped Item
    content: @Composable () -> Unit //Custom Content View
)

### Features

- Swipable Cards Component to left and right off the screen
- It can be useful in a Tinder like application
- Easy plug-and-play component with custom Content Field


