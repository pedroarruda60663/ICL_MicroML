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
L0:
new frame_0
dup
invokespecial frame_0/<init>()V
dup
aload 0
putfield frame_0/sl Ljava/lang/Object;
astore 0
aload 0
new ref_int
dup
invokespecial ref_int/<init>()V
dup
sipush 2
putfield ref_int/value I
putfield frame_0/loc_0 Lref_int;
new frame_1
dup
invokespecial frame_1/<init>()V
dup
aload 0
putfield frame_1/sl Lframe_0;
astore 0
aload 0
new ref_int
dup
invokespecial ref_int/<init>()V
dup
sipush 3
putfield ref_int/value I
putfield frame_1/loc_0 Lref_int;
aload 0
getfield frame_1/loc_0 Lref_int;
aload 0
getfield frame_1/sl Lframe_0;
getfield frame_0/loc_0 Lref_int;
getfield ref_int/value I
sipush 2
iadd
putfield ref_int/value I
aload 0
getfield frame_1/sl Lframe_0;
astore 0
aload 0
getfield frame_0/sl Ljava/lang/Object;
astore 0
return
.end method
