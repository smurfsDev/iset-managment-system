<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;

class TechnicienController extends Controller
{
    public function show(Request $request)
    {
        $user = $request->user()->department()->get();
        $users = User::whereHas('roles', function ($query) use ($user) {
            $query->where('name', 'technicien')
                ->where('department', $user[0]->id);
        })->with('roles')
            ->paginate(5);

        if (empty($users)) {
            return response()->json(['message' => 'No Technicien found'], 404);
        } else {
            return response()->json($users, 200);
        }
    }
    public function accept($id)
    {
        $user = User::find($id);
        if ($user) {
            $user->roles()->updateExistingPivot(6, ['status' => 1]);
            $user->save();
            return response()->json('technicien accepted', 200);
        }
        return response()->json('technicien not found', 404);
    }

    public function refuse($id)
    {
        $user = User::find($id);
        $user->roles()->updateExistingPivot(6, ['status' => 2]);
        $user->save();
        return response()->json('technicien refused', 200);
    }


}
