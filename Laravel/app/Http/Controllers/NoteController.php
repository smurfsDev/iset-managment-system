<?php

namespace App\Http\Controllers;

use App\Models\Classe;
use App\Models\Note;
use Illuminate\Http\Request;

class NoteController extends Controller
{
    public function setNote(Request $request)
    {
        $n = Note::where('student_id', $request->student_id)
            ->where('matiere_id', $request->matiere_id)
            ->first();
        if (!empty($n)) {
            $n->note = $request->note;
            $n->save();
        } else {
            Note::create([
                'student_id' => $request->student_id,
                'matiere_id' => $request->matiere_id,
                'note' => $request->note,
            ]);
        }

        return response()->json(['success' => 'Note saved successfully.']);
    }

}
