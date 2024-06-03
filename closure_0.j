.class public closure_0
.super java/lang/Object
.implements fun_(int_int_int)
.field public sl Ljava/lang/Object;

.method public <init>()V
aload_0
invokenonvirtual java/lang/Object/<init>()V
return
.end method

.method public apply(II)I
.limit locals 4
new frame_0
dup
invokespecial frame_0/<init>()V
dup
aload 0
getfield closure_0/sl Ljava/lang/Object;
putfield frame_0/sl Ljava/lang/Object;
dup
aload 1
putfield frame_0/loc_0 I
aload 2
putfield frame_0/loc_1 I
astore 0
return.end method