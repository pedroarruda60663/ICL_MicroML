.class public Demo
.super java/lang/Object
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method
.method public static main([Ljava/lang/String;)V
 .limit locals 10
 .limit stack 256
 getstatic java/lang/System/out Ljava/io/PrintStream;
L1:
new frame_0
dup
invokespecial frame_0/<init>()V
dup
aload 0
putfield frame_0/sl Ljava/lang/Object;
astore 0
aload 0
sipush 5
newarray byte
putfield frame_0/loc_0 [B
aload 0
getfield frame_0/loc_0 [B
sipush 2
sipush 1
bastore
aload 0
getfield frame_0/loc_0 [B
sipush 2
baload
aload 0
getfield frame_0/sl Ljava/lang/Object;
astore 0
return
.end method
