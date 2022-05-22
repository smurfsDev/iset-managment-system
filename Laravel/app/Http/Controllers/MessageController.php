<?php

namespace App\Http\Controllers;

use App\Models\Message;
use App\Models\User;
use Illuminate\Http\Request;

class MessageController extends Controller
{
    public function index(Request $request ,$id)
    {
        $user = $request->user()->id;
        $messages = Message::with('user.messages')
                            ->with('receiver.messages')
                            // ->where('user_id','=',$user)
                            // ->where('receiver_id','=',$id)

                            ->where([['user_id','=',$user],['receiver_id','=', $id]])
                            ->orWhere([['receiver_id','=',$user],['user_id','=', $id]])
                            ->get();


        if (sizeof($messages) > 0) {
            return response()->json( $messages, 200);
        } else
            return response()->json([
                "aucune Messages"
            ], 404);

    }
    public function users(Request $request){
        $user = $request->user()->id;
        $users=User::where('id','!=',$user)->get();
        if (sizeof($users) > 0) {
            return response()->json( $users, 200);
        } else
            return response()->json([
                "aucune user"
            ], 404);

    }
}
