<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;

class EnseignantController extends Controller
{
    public function show(Request $request)
    {
        $user = $request->user()->department()->get();
        $users = User::whereHas('roles', function ($query) use ($user) {
            $query->where('name', 'enseignant')
                ->where('department', $user[0]->id);
        })->with('roles')
            ->paginate(5);

        if (empty($users)) {
            return response()->json(['message' => 'No Enseignant found'], 404);
        } else {
            return response()->json($users, 200);
        }
    }
    public function accept($id)
    {
        $user = User::find($id);
        if ($user) {
            $user->roles()->updateExistingPivot(7, ['status' => 1]);
            $user->save();
            return response()->json('Enseignant accepted', 200);
        }
        return response()->json('Enseignant not found', 404);
    }

    public function refuse($id)
    {
        $user = User::find($id);
        $user->roles()->updateExistingPivot(7, ['status' => 2]);
        $user->save();
        return response()->json('Enseignant refused', 200);
    }
}
