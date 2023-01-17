# Cast Image not loading PoC 

This showcases issues with the Android Cast Sender SDK not being able to load images, even though they have been supplied. To reproduce the issue with this PoC please follow:

- Open App
- Connect to Cast receiver
- Observe the image that will be loaded at the top of the page
- Press "Start Video"

Correct: Observe the image loading

- Press "Asset 2"
- Observe the to-use image has changed
- Press "Start Video"

Correct: Observe the image has changed and still loading

- Press "No Asset"
- Observe the to-use image being empty
- Press "Start Video"

Correct: Observe the image being empty

- Press "Asset 2" again
- Observe the to-use image has changed back to the previous asset
- Press "Start Video"

Wrong: Observe the image does not load. 

No amount of stopping the video with Asset 2 or switching back to the empty asset will reload the image. However the image can be shown again by either of:

1:
- Be in the state of the image not loading
- Keep casting but exit the app
- Close app completely 
- Re-open app, it should still be casting
- The image will be there again, even no new stream has been loaded

2:
- Be in the state of the image not loading
- Switch back to the other working asset and start the stream
- It loads fine
- Switch back to the non-loading asset and start the stream
- It loads fine
