import { Injectable } from '@nestjs/common';
import { TeacherRepo } from '../repository/teacher.repo';
import { Teacher } from '../schemas/teacher.schema';

@Injectable()
export class TeacherService {
    constructor(
        private readonly teacherRepo: TeacherRepo
    ) { }

    async findAll(): Promise<Teacher[]> {
        return this.teacherRepo.findAll();
    }

    async create(data): Promise<Teacher> {
        data.createddate = new Date();
        return this.teacherRepo.create(data);
    }

    async update(teacherId, data): Promise<Teacher> {
        return this.teacherRepo.update(teacherId, data);
    }

    async delete(teacherId): Promise<Teacher> {
        return this.teacherRepo.delete(teacherId);
    }
}